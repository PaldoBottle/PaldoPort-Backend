package paldo_bottle.backend.stamp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paldo_bottle.backend.DTO.GetStampDetailRes;
import paldo_bottle.backend.DAO.OwnStamp;
import paldo_bottle.backend.DAO.Stamp;
import paldo_bottle.backend.DAO.User;
import paldo_bottle.backend.DAO.embedded.RegionID;
import paldo_bottle.backend.DTO.GetStampListReq;
import paldo_bottle.backend.DTO.GetStampListResItem;
import paldo_bottle.backend.DTO.PublishStampDtoReq;
import paldo_bottle.backend.DTO.PublishStampDtoRes;
import paldo_bottle.backend.global.exception.BaseException;
import paldo_bottle.backend.global.exception.BaseResponseStatus;
import paldo_bottle.backend.stamp.repository.StampRepository;
import paldo_bottle.backend.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StampService {

    private final StampRepository stampRepository;
    private final UserRepository userRepository;

    @Transactional
    public PublishStampDtoRes publishStamp(String userId, PublishStampDtoReq publishStampDtoReq) throws BaseException {
        User user = getUser(userId);
        Optional<Stamp> optionalStamp = stampRepository.findById(
                new RegionID(
                        publishStampDtoReq.getSupDistrict(),
                        publishStampDtoReq.getDistrict()
                )
        );
        if (optionalStamp.isEmpty())
            throw new BaseException(BaseResponseStatus.NOT_EXIST_STAMP);
        Stamp stamp = optionalStamp.get();
        OwnStamp ownStamp = OwnStamp.createOwnStamp(user, stamp);
        user.addStamps(ownStamp);
        stamp.addOwner(ownStamp);
        userRepository.save(user);
        stampRepository.save(stamp);
        return PublishStampDtoRes.builder()
                .publishDate(ownStamp.getPublish_date())
                .supDistrict(stamp.getRegion().getLocation().getSupDistrict())
                .district(stamp.getRegion().getLocation().getDistrict())
                .publishNumber(ownStamp.getPublish_number())
                .build();
    }
    public List<GetStampListResItem> getStampList(String userId, GetStampListReq req) throws BaseException {
        User user = getUser(userId);
        List<Stamp> haveStamps = this.stampRepository.findAllWithUsers(userId);
        List<Stamp> allStamps = this.stampRepository.findAll();
        return allStamps.stream()
                .map((stamp) -> {
                return GetStampListResItem.builder()
                        .supDistrict(stamp.getLocation().getSupDistrict())
                        .district(stamp.getLocation().getDistrict())
                        .have(haveStamps.contains(stamp))
                        .build();
                })
                .collect(Collectors.toList());
    }

    public GetStampDetailRes getStampDetail(String userId, String supDistrict, String district) throws BaseException {
        User user = getUser(userId);
        Optional<OwnStamp> userStamp = user.getOwnStamps()
                .stream()
                .filter((ownStamp -> {
                    RegionID location = ownStamp.getOwnStampID().getLocation();
                    return (location.getDistrict() == district && location.getSupDistrict() == supDistrict);
                }))
                .findFirst();
        if (userStamp.isPresent()) {
            OwnStamp ownStamp = userStamp.get();
            return new GetStampDetailRes(
                    ownStamp.getStamp().getPoint(),
                    ownStamp.getPublish_date(),
                    ownStamp.getPublish_number()
            );
        }
        Optional<Stamp> optionalStamp = stampRepository.findById(new RegionID(supDistrict, district));
        if (optionalStamp.isEmpty()) throw new BaseException(BaseResponseStatus.NOT_EXIST_STAMP);
        Stamp stamp = optionalStamp.get();
        return new GetStampDetailRes(
                stamp.getPoint(), null, null
        );
    }


    private User getUser(String userId) throws BaseException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty())
            throw new BaseException(BaseResponseStatus.NOT_EXIST_USER);
        User user = optionalUser.get();
        return user;
    }
}
