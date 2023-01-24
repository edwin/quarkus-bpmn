package com.edw.service;

import com.edw.entity.Blacklist;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

/**
 * <pre>
 *     com.edw.service.BlacklistService
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 24 Jan 2023 14:37
 */
@ApplicationScoped
@Transactional
public class BlacklistService {
    public Boolean isBlacklist(String name) {
        if(name == null || name.trim().isEmpty())
            return true;
        return !Blacklist.find("name", name).list().isEmpty();
    }
}
