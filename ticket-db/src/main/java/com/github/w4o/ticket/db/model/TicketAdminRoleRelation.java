package com.github.w4o.ticket.db.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class TicketAdminRoleRelation implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ticket_admin_role_relation.id
     *
     * @mbg.generated Fri May 10 14:52:53 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ticket_admin_role_relation.admin_id
     *
     * @mbg.generated Fri May 10 14:52:53 CST 2019
     */
    private Integer adminId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ticket_admin_role_relation.role_id
     *
     * @mbg.generated Fri May 10 14:52:53 CST 2019
     */
    private Integer roleId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ticket_admin_role_relation
     *
     * @mbg.generated Fri May 10 14:52:53 CST 2019
     */
    private static final long serialVersionUID = 1L;
}