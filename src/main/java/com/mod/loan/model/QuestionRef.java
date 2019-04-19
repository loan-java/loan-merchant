package com.mod.loan.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_question_ref")
public class QuestionRef {
    @Id
    private Integer id;

    /**
     * 类型id
     */
    @Column(name = "type_id")
    private Long typeId;

    /**
     * 文章id
     */
    @Column(name = "article_id")
    private Long articleId;

    /**
     * 排序
     */
    private Integer idx;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取类型id
     *
     * @return type_id - 类型id
     */
    public Long getTypeId() {
        return typeId;
    }

    /**
     * 设置类型id
     *
     * @param typeId 类型id
     */
    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取文章id
     *
     * @return article_id - 文章id
     */
    public Long getArticleId() {
        return articleId;
    }

    /**
     * 设置文章id
     *
     * @param articleId 文章id
     */
    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    /**
     * 获取排序
     *
     * @return idx - 排序
     */
    public Integer getIdx() {
        return idx;
    }

    /**
     * 设置排序
     *
     * @param idx 排序
     */
    public void setIdx(Integer idx) {
        this.idx = idx;
    }
}