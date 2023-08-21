package paldo_bottle.backend.stamp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paldo_bottle.backend.DAO.OwnStamp;
import paldo_bottle.backend.DAO.Stamp;
import paldo_bottle.backend.DAO.User;
import paldo_bottle.backend.DAO.embedded.RegionID;
import paldo_bottle.backend.DTO.PublishStampDtoReq;
import paldo_bottle.backend.DTO.PublishStampDtoRes;
import paldo_bottle.backend.global.exception.BaseException;
import paldo_bottle.backend.global.exception.BaseResponseStatus;
import paldo_bottle.backend.stamp.repository.StampRepository;
import paldo_bottle.backend.user.repository.UserRepository;

import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StampService {

    private final StampRepository stampRepository;
    private final UserRepository userRepository;

    @Transactional
    public PublishStampDtoRes publishStamp(String userId, PublishStampDtoReq publishStampDtoReq) throws BaseException {

        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty())
            throw new BaseException(BaseResponseStatus.NOT_EXIST_USER);
        User user = optionalUser.get();
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

}
