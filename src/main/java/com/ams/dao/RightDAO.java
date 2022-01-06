package com.ams.dao;

import com.ams.service.po.RightPO;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Responsible for db operations with Right.
 *
 * @author Alexey Mironov
 */
public interface RightDAO {

    /**
     * Get map of role id to role rights.
     *
     * @param roleIds - role ids
     * @return map of role id to role rights.
     */
    Map<Long, List<RightPO>> getRights(Collection<Long> roleIds);
}
