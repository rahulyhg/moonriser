package be.persgroep.pe.service.domain.component;

import javax.validation.constraints.NotNull;

/**
 * Created by jlust on 1/12/14.
 */
public class Contributor {
    @NotNull
    private Long authorId;
    @NotNull
    private ContributorRole role;

    public Contributor() {
    }

    public Contributor(final Long authorId, final ContributorRole role) {
        this.authorId = authorId;
        this.role = role;
    }

    public Long getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(final Long authorId) {
        this.authorId = authorId;
    }

    public ContributorRole getRole() {
        return this.role;
    }

    public void setRole(final ContributorRole role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result
                + ((this.authorId == null) ? 0 : this.authorId.hashCode());
        result = prime * result
                + ((this.role == null) ? 0 : this.role.hashCode());

        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Contributor other = (Contributor) obj;
        if (this.authorId == null) {
            if (other.authorId != null)
                return false;
        } else if (!this.authorId.equals(other.authorId))
            return false;
        if (this.role != other.role)
            return false;

        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append("Contributor [role=");
        builder.append(this.role);
        builder.append(", authorId=");
        builder.append(this.authorId);
        builder.append("]");

        return builder.toString();
    }

}
