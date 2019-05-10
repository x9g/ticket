package com.github.w4o.ticket.db.mapper;

import com.github.w4o.ticket.db.model.TicketAdmin;
import com.github.w4o.ticket.db.model.TicketAdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TicketAdminMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket_admin
     *
     * @mbg.generated Fri May 10 14:52:53 CST 2019
     */
    long countByExample(TicketAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket_admin
     *
     * @mbg.generated Fri May 10 14:52:53 CST 2019
     */
    int deleteByExample(TicketAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket_admin
     *
     * @mbg.generated Fri May 10 14:52:53 CST 2019
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket_admin
     *
     * @mbg.generated Fri May 10 14:52:53 CST 2019
     */
    int insert(TicketAdmin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket_admin
     *
     * @mbg.generated Fri May 10 14:52:53 CST 2019
     */
    int insertSelective(TicketAdmin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket_admin
     *
     * @mbg.generated Fri May 10 14:52:53 CST 2019
     */
    List<TicketAdmin> selectByExample(TicketAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket_admin
     *
     * @mbg.generated Fri May 10 14:52:53 CST 2019
     */
    TicketAdmin selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket_admin
     *
     * @mbg.generated Fri May 10 14:52:53 CST 2019
     */
    int updateByExampleSelective(@Param("record") TicketAdmin record, @Param("example") TicketAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket_admin
     *
     * @mbg.generated Fri May 10 14:52:53 CST 2019
     */
    int updateByExample(@Param("record") TicketAdmin record, @Param("example") TicketAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket_admin
     *
     * @mbg.generated Fri May 10 14:52:53 CST 2019
     */
    int updateByPrimaryKeySelective(TicketAdmin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket_admin
     *
     * @mbg.generated Fri May 10 14:52:53 CST 2019
     */
    int updateByPrimaryKey(TicketAdmin record);
}