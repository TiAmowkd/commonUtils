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
    private Integer pageNum;
    private Integer pageSize;
    private Integer totalPage;
    private Long total;

    public static <T> PageResponseDTO<T> restPage(List<T> list) {
        PageResponseDTO<T> responseDTO = new PageResponseDTO<>();
//        Page<T> pageInfo = new PageInfo<T>(list);
//        BeanUtils.copyProperties(pageInfo, responseDTO);
//        responseDTO.setList(pageInfo.getList());
//        responseDTO.setPageNum(pageInfo.getPageNum());
//        responseDTO.setPageSize(pageInfo.getPageSize());
//        responseDTO.setTotal(pageInfo.getTotal());
        return responseDTO;
    }
}
