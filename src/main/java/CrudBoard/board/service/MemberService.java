package CrudBoard.board.service;

import CrudBoard.board.domain.Member;
import CrudBoard.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 아이디는 중복 불가능
     * 사용자 이름도 중복 불가능(구현 예정)
     */
    private void validateDuplicateMember(Member member) {
        Optional<Member> findMember = findByUsername(member.getUsername());
        if (!findMember.isPresent()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 조회
     */
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

    public Optional<Member> findByUsername(String username){
        return Optional.ofNullable(memberRepository.findByName(username));
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }
}
