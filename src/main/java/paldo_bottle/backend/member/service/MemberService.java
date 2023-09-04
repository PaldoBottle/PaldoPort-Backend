package paldo_bottle.backend.member.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import paldo_bottle.backend.DAO.User;
import paldo_bottle.backend.DTO.member.MemberInfo;
import paldo_bottle.backend.global.exception.BaseException;
import paldo_bottle.backend.global.exception.BaseResponseStatus;
import paldo_bottle.backend.member.repository.MemberRepository;

@Slf4j
@Service
public class MemberService {
    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public MemberInfo getMember(String user_Id) throws BaseException {
        User user = repository.findById(user_Id).get();
        if(user == null) {
            throw new BaseException(BaseResponseStatus.NOT_EXIST_USER);
        }
        MemberInfo memberInfo = new MemberInfo().builder()
                .id(user.getId())
                .point(user.getPoint())
                .profileImg(user.getProfileImg())
                .build();
        return memberInfo;
    }

    public User joinMember(User user) {
        return repository.save(user);
    }


    public MemberInfo joinMember(String user_id, String user_profile_image) {
        User newb = new User(user_id, user_profile_image);
        repository.save(newb);

        return new MemberInfo().builder()
                .id(user_id)
                .point(0L)
                .profileImg(user_profile_image)
                .build();

    }
}
