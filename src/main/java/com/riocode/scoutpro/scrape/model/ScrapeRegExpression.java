package com.riocode.scoutpro.scrape.model;

import com.riocode.scoutpro.scrape.enums.ScrapeField;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Data
@Entity
@Table(name = "scrape_reg_expression")
public class ScrapeRegExpression implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name= "field_name")
    @Enumerated(EnumType.STRING)
    private ScrapeField fieldName;

    @Basic(optional = false)
    @Column(name = "regex")
    private String regex;

}
