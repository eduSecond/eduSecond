package net.fullstack7.edusecond.edusecond.mapper;

import net.fullstack7.edusecond.edusecond.domain.AdminVO;

public interface AdminMapper {
    AdminVO selectAdminById(String adminId);
}
