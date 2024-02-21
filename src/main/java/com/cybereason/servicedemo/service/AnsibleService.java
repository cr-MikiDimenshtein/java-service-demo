package com.cybereason.servicedemo.service;


import com.github.woostju.ansible.AnsibleClient;
import com.github.woostju.ansible.command.PlaybookCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AnsibleService {

    private final AnsibleClient ansibleClient;

    @Autowired
    public AnsibleService(AnsibleClient ansibleClient) {
        this.ansibleClient = ansibleClient;
    }

    public void runPlaybook(String playbookPath) {
        ansibleClient.execute(new PlaybookCommand(Collections.emptyList(), playbookPath, Collections.emptyList()), 10);
    }
}
