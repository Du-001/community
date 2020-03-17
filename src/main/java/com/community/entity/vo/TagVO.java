package com.community.entity.vo;

import com.community.entity.BaseEntity;
import com.community.entity.domain.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

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
public class TagVO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 描述
     */
    private String name;

    /**
     * 子标签
     */
    private List<Tag> tags;

}
