package com.edw.service;

import com.edw.entity.Blacklist;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

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
        return !Blacklist.find("name", name).list().isEmpty();
    }
}
