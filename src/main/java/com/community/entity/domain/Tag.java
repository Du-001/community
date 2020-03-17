package com.community.entity.domain;

import com.community.entity.BaseEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author du
 * @since 2020-03-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Tag extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 父标签id
     */
    private Long parentId;

    /**
     * 描述
     */
    private String name;


}
