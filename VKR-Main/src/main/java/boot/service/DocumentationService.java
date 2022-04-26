package boot.service;

import boot.DTO.DocumentationDTO;
import boot.mapstruct.DocumentationMapper;
import boot.repository.DocumentationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentationService implements ServiceMag<DocumentationDTO> {
    DocumentationRepository documentationRepository;
    DocumentationMapper documentationMapper;

    @Autowired
    public DocumentationService(DocumentationRepository documentationRepository, DocumentationMapper documentationMapper) {
        this.documentationRepository = documentationRepository;
        this.documentationMapper = documentationMapper;
    }

    public void setDocumentationRepository(DocumentationRepository documentationRepository) {
        this.documentationRepository = documentationRepository;
    }

    @Override
    public DocumentationDTO get(long id) {

        return documentationMapper.documentationToDocumentationDto(
                documentationRepository.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public List<DocumentationDTO> getAll() {
        return documentationRepository.findAll().stream()
                .map(value-> documentationMapper.documentationToDocumentationDto(value))
                .collect(Collectors.toList());
    }

    @Override
    public void save(DocumentationDTO entity) {
        documentationRepository.save(documentationMapper.documentationDtoToDocumentation(entity));
    }

    @Override
    public void delete(long id) {
        documentationRepository.deleteById(id);
    }
}
