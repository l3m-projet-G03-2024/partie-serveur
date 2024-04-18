package fr.uga.l3miage.integrator.cyberCommandes.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeTournee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TourneeEntity {

@Id
@Column(name = "refrencen_tournee")
private String reference;

private EtatsDeTournee etatDeLaTournee;

private Integer tdrTheorique;

private Integer tdrEffectif;


@OneToMany
private Set<LivraisonEntity> livrasions;




}
