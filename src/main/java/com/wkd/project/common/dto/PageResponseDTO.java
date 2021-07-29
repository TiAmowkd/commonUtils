package com.wkd.project.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author wkd
 * @version 1.0.0
 * @className PageResponseModels.java
 * @description 分页数据封装体
 * @createTime 2021-07-29 11:37
 */
@Data
public class PageResponseDTO<T> implements Serializable {
    private static final long serialVersionUID = -1852829979223997296L;
    private List<T> list;
    private Long pageNumber;
    private Long total;

}
