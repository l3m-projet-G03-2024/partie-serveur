package fr.uga.l3miage.integrator.cyberCommandes.components;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import fr.uga.l3miage.integrator.cyberCommandes.repositories.JourneeRepository;

@Component
@RequiredArgsConstructor
public class JourneeComponent {
    private final JourneeRepository journeeRepository;
}