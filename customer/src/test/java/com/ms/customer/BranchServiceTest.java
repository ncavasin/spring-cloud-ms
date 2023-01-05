package com.ms.customer;

import com.ms.customer.branch.entity.Branch;
import com.ms.customer.branch.entity.dto.BranchDto;
import com.ms.customer.branch.service.BranchService;
import org.junit.Before;
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
    void setUp() {
        this.createdBranch = this.branchService.add(BranchDto.builder()
                .name("BRANCH_NAME")
                .zipCode("6700")
                .build());
    }


}
