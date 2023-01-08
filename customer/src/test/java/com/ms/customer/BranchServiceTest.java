package com.ms.customer;

import com.ms.customer.branch.entity.Branch;
import com.ms.customer.branch.entity.dto.BranchDto;
import com.ms.customer.branch.service.BranchService;
import com.ms.customer.shared.exceptions.BadRequest;
import com.ms.customer.shared.exceptions.NotFound;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

@RunWith(SpringRunner.class)
@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
public class BranchServiceTest {

    @Autowired
    private BranchService branchService;

    private Branch createdBranch;

    @Before
    @Transactional
    public void setUp() {
        this.createdBranch = this.branchService.add(BranchDto.builder()
                .name("BRANCH_NAME")
                .zipCode("6700")
                // rooms are cascaded
                .build());
    }

    @Test
    @Transactional
    public void createBranch() {
        Assert.assertEquals(this.createdBranch, this.branchService.findById(this.createdBranch.getId()));
    }

    @Test
    @Transactional
    @Ignore
    public void createBranchWithoutName_throwsBadRequest() {
        this.branchService.add(BranchDto.builder()
                .zipCode("zip code")
                .build());
        Assert.assertThrows(BadRequest.class, () -> this.branchService.add(BranchDto.builder()
                .zipCode("zip code")
                .build()));
    }

    @Test
    @Transactional
    public void findByName() {
        Assert.assertEquals(this.createdBranch, this.branchService.findByName(this.createdBranch.getName()));
    }

    @Test
    @Transactional
    public void updateBranchName() {
        final String newName = "NEW BRANCH NAME";
        this.branchService.update(this.createdBranch.getId(), BranchDto.builder()
                .name(newName)
                .build());
        Assert.assertEquals(newName, this.branchService.findById(this.createdBranch.getId()).getName());
    }

    @Test
    @Transactional
    public void updateBranchWithTakenName_throwsBadRequest() {
        final String repeatedName = "REPEATED NAME";
        this.branchService.add(BranchDto.builder()
                .name(repeatedName)
                .zipCode("random zip code")
                .build());
        Assert.assertThrows(BadRequest.class, () -> this.branchService.update(this.createdBranch.getId(),
                BranchDto.builder()
                        .name(repeatedName)
                        .build()));
    }

    @Test
    @Transactional
    public void deleteBranch() {
        this.branchService.delete(this.createdBranch.getId());
        Assert.assertThrows(NotFound.class, () -> this.branchService.findById(this.createdBranch.getId()));
    }

}
