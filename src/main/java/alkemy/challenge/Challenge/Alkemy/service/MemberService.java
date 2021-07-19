package alkemy.challenge.Challenge.Alkemy.service;

import alkemy.challenge.Challenge.Alkemy.model.Member;
import alkemy.challenge.Challenge.Alkemy.repository.MemberRepository;
import alkemy.challenge.Challenge.Alkemy.util.Message;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public List<Member> memberList(){
        return (List<Member>) memberRepository.findAll();
    }

    public Member findById(Long id){
        return memberRepository.findById(id).orElse(null);
    }

    public ResponseEntity<?> create(Member member) {
        if (StringUtils.isBlank(member.getName())) {
            return new ResponseEntity(new Message("El campo 'nombre' está vacío"),
                    HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(member.getImage())) {
            return new ResponseEntity(new Message("El campo 'image' está vacío"),
                    HttpStatus.BAD_REQUEST);
        }
        memberRepository.save(member);
        return ResponseEntity.ok("miembro creado con éxito");
    }

    public Page<Member> findAll(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

    public void save(Member member){
        memberRepository.save(member);
    }

    public ResponseEntity<?> delete(Long id){
        Optional<Member> member = memberRepository.findByIdAndDeletedFalse(id);
        if (member.isEmpty()) {
            return new ResponseEntity("no se ha encontrado un miembro con el id: "+id,HttpStatus.NOT_FOUND);
        }
        memberRepository.delete(member.get());
        return ResponseEntity.ok("miembro eliminado con exito");
    }

    public ResponseEntity<?> update(Member member, Long id) {
        Optional<Member> memberAux = memberRepository.findByIdAndDeletedFalse(id);
        if (memberAux.isEmpty()){
            return new ResponseEntity(new Message("no se ha encontrado un miembro con el id: "+id),
                    HttpStatus.NOT_FOUND);
        }
        memberAux.get().setName(member.getName());
        memberAux.get().setFacebookUrl(member.getFacebookUrl());
        memberAux.get().setInstagramUrl(member.getInstagramUrl());
        memberAux.get().setInstagramUrl(member.getLinkedinUrl());
        memberAux.get().setImage(member.getImage());//cambiar por lo de amazon
        memberAux.get().setDescription(member.getDescription());
        memberRepository.save(memberAux.get());
        return ResponseEntity.ok("miembro actualizado con exito");
    }
}
