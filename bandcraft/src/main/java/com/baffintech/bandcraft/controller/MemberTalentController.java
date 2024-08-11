package com.baffintech.bandcraft.controller;

import com.baffintech.bandcraft.database.dao.MemberDAO;
import com.baffintech.bandcraft.database.dao.MemberTalentDAO;
import com.baffintech.bandcraft.database.dao.TalentDAO;
import com.baffintech.bandcraft.database.entity.Band;
import com.baffintech.bandcraft.database.entity.Member;
import com.baffintech.bandcraft.database.entity.MemberTalent;
import com.baffintech.bandcraft.database.entity.Talent;
import com.baffintech.bandcraft.form.CreateBandFormBean;
import com.baffintech.bandcraft.form.CreateMemberFormBean;
import com.baffintech.bandcraft.form.CreateMemberTalentFormBean;
import com.baffintech.bandcraft.security.AuthenticatedUserUtilities;
import com.baffintech.bandcraft.service.MemberService;
import com.baffintech.bandcraft.service.MemberTalentService;
import com.baffintech.bandcraft.service.TalentService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/member-talent")    // part of URL before the jsp page
public class MemberTalentController {

    @Autowired
    private MemberTalentDAO memberTalentDAO;

    @Autowired
    private MemberTalentService memberTalentService;

    @Autowired
    private MemberDAO memberDAO;

    @Autowired
    private MemberService memberService;

    @Autowired
    private TalentDAO talentDAO;

    @Autowired
    private TalentService talentService;
    @Autowired
    private AuthenticatedUserUtilities authenticatedUserUtilities;

    // listens on url: localhost:8080/member-talent/list
    @GetMapping("/list")
    public ModelAndView findAll() {

        ModelAndView response = new ModelAndView("member-talent/list");
        List<MemberTalent> memberTalents = memberTalentDAO.findAll();
        response.addObject("memberTalentsKey", memberTalents);

        return response;
    }

    // listens on url: localhost:8080/member-talent/id
    @GetMapping("/{id}")
    public ModelAndView detail(@PathVariable Integer id) {

        ModelAndView response = new ModelAndView("member-talent/detail");
        log.debug("The user wants the member-talent with id:  " + id);

        MemberTalent memberTalent = memberTalentDAO.findById(id);
        response.addObject("memberTalentKey", memberTalent);

        return response;
    }

    @GetMapping("/create")
    public ModelAndView create(@RequestParam(required=false) Integer memberId) {                                        // this method is setting up the view for rendering

        if (memberId == null) {     //e.g. lost, weird navigation
            Member member = new Member();
            member = memberDAO.findByUser(authenticatedUserUtilities.getCurrentUser());

            ModelAndView response = new ModelAndView("member-talent/create");

            List<Talent> talents = talentDAO.findAll();
            response.addObject("talentsKey", talents);
            return response;

        } else {
            ModelAndView response = new ModelAndView("member-talent/create");

            response.addObject("memberIdKey", memberId);

            List<Talent> talents = talentDAO.findAll();
            response.addObject("talentsKey", talents);

            List<MemberTalent> existingMemberTalents = memberTalentDAO.findByMemberId(memberId);
            response.addObject("existingMemberTalentsKey", existingMemberTalents);

            return response;
        }
    }

    @GetMapping("/createSubmit")
    public ModelAndView createSubmit(@RequestParam (required=false) String memberId, @RequestParam (required=false) String talentId) {

        ModelAndView response = new ModelAndView();
        log.debug("Member id: " + memberId + "   TalentId: " + talentId);                                               // prints the form data to the console using the CreateMemberFormBean form

        MemberTalent memberTalent = memberTalentService.createMemberTalent(Integer.valueOf(memberId), Integer.valueOf(talentId));                         // saves the memberTalent to the db

        response.setViewName("redirect:/member-talent/create?memberId=" + memberId);                                    // this is a URL, NOT a view name
        //response.setViewName("redirect:/member-talent/create");

        return response;
    }
}

    // listens on url: localhost:8080/member-talent/search
//    @GetMapping("/search")
//    public ModelAndView search(@RequestParam(required = false) String search) {
//
//        ModelAndView response = new ModelAndView("member-talent/search");
//        log.debug("The user searched for the term: " + search);
//
//        // Add the user input back to the model so that we can display the search term in the input field
//        response.addObject("searchKey", search);
//
//        List<MemberTalent> memberTalents = memberTalentDAO.findByMemberOrTalent(search);
//        response.addObject("memberTalentsKey", memberTalents);
//
//        return response;
//    }
