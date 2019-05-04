package br.ufmt.ic.fata.PranchaComunicacao.model.base;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.springframework.data.domain.Persistable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.util.UUID;

@MappedSuperclass
@Getter @Setter @NoArgsConstructor @ToString
public abstract class EntidadeBase implements Persistable<Long> {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.PROTECTED) // Só subclasses podem mudar o ID
    protected Long id;
    
    /* Usado para comparação (equals/hashcode) */
    /* https://stackoverflow.com/questions/6033905/create-the-perfect-jpa-entity/14822709 */
    /* https://dzone.com/articles/why-should-you-care-about-equals-and-hashcode */
    @Type(type = "pg-uuid") @Setter(AccessLevel.NONE)
    private UUID uuid = UUID.randomUUID();
    
    /* Hibernate saber se está com a entidade atualizada em memória */
    @Version
    private Long version;
    
    @JsonIgnore
    @Override
    public boolean isNew() {
        return id == null;
    }
    
    @JsonIgnore
    public UUID getUuid() {
        return uuid;
    }
    
    @JsonIgnore
    public Long getVersion() {
        return version;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        EntidadeBase that = (EntidadeBase) o;
        
        return uuid.equals(that.uuid);
    }
    
    @Override
    public int hashCode() {
        return uuid.hashCode();
    }
    
}
