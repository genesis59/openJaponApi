package com.gregdev.openjapon.core.api;

import com.gregdev.openjapon.core.dto.KanjiDto;
import com.gregdev.openjapon.core.entity.Kanji;
import com.gregdev.openjapon.core.service.KanjiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kanji")
public class KanjiResource {

    @Autowired
    private KanjiService kanjiService;

    /***********************************************************/
    /**                      ROUTES GET                       **/
    /***********************************************************/

    /**
     * Filtre et pagine une liste de kanjis
     *
     * @param strokes   Byte
     * @param meaningFr String
     * @param size      String
     * @param page      String
     * @return ResponseEntity<Map < String, Object>>
     */
    @GetMapping
    @Operation(summary = "Filtrer et paginer une liste de kanjis")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Kanjis trouvé",
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Kanji.class))
                    )}),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erreur serveur",
                    content = @Content),
    })
    public ResponseEntity<Map<String, Object>> list(
            @RequestParam(required = false) Byte strokes,
            @RequestParam(required = false) String meaningFr,
            @RequestParam(defaultValue = "5") String size,
            @RequestParam(defaultValue = "1") String page) {
        try {
            Map<String, Object> response = kanjiService.getKanjiBy(meaningFr, strokes, PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(size)));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * getKanjiById
     *
     * @param id Long
     * @return ResponseEntity<Optional < Kanji>>
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un kanji par son id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Kanji trouvé",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Kanji.class)
                    )}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Aucun kanji ne correspond à cet identifiant",
                    content = @Content),
    })
    public ResponseEntity<Kanji> getKanjiById(@Parameter(description = "id du Kanji recherché") @PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(kanjiService.getKanjiById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /***********************************************************/
    /**                     ROUTES POST                       **/
    /***********************************************************/

    /**
     * Enregistre un kanji en base de données
     *
     * @param kanjiDto KanjiDto
     * @return ResponseEntity<Kanji>
     */
    @PostMapping
    @Operation(summary = "Ajouter un kanji à la base de données")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Kanji ajouté à la base de données",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Kanji.class)
                    )}),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erreur serveur",
                    content = @Content),
    })
    public ResponseEntity<Kanji> save(@RequestBody KanjiDto kanjiDto) {
        try {
            return new ResponseEntity<>(kanjiService.add(kanjiDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Enregistre une liste de kanji en base de données
     *
     * @param kanjisDto List<KanjiDto>
     * @return ResponseEntity<List < Kanji>>
     */
    @PostMapping("/all")
    @Operation(summary = "Ajouter une liste de kanji en base de données")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Kanjis ajoutés à la base de données",
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Kanji.class))
                    )}),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erreur serveur",
                    content = @Content),
    })
    public ResponseEntity<List<Kanji>> saveAll(@RequestBody List<KanjiDto> kanjisDto) {
        try {
            return new ResponseEntity<>(kanjiService.addAll(kanjisDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
