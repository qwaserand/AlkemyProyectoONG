package alkemy.challenge.Challenge.Alkemy.controller;

import alkemy.challenge.Challenge.Alkemy.model.Member;
import alkemy.challenge.Challenge.Alkemy.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public Page<Member> listMembers(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return memberService.findAll(pageable);
    }

    @PostMapping
    public ResponseEntity<?> newMember(@RequestBody @Valid Member member) {
        return memberService.create(member);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable Long id) {
        return memberService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editMember(@PathVariable Long id, @RequestBody @Valid Member member) {
        return memberService.update(member, id);
    }


}
