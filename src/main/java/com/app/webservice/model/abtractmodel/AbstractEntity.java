package com.app.webservice.model.abtractmodel;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@ToString
public abstract class AbstractEntity {

    @CreatedBy
    @Column(name = "created_by", updatable = false, length = 50)
    private String createdBy;

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonDeserialize(using = DateDeserializers.TimestampDeserializer.class)
    private Date createdDate;

    @LastModifiedBy
    @Column(name = "last_modified_by", insertable = false, length = 50)
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified_date", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonDeserialize(using = DateDeserializers.TimestampDeserializer.class)
    private Date lastModifiedDate;
}
