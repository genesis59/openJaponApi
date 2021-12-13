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
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kanji")
public class KanjiResource {

    @Autowired
    private KanjiService kanjiService;

    public static final String ERROR_FIELD = "Les champs de recherche meaningFr, readingKun, readingOn et idKeyKanji ne doivent pas être utilisés simultanément.";
    public static final String ERROR_NOT_FOUND = "Aucun kanji ne correspond à votre recherche.";
    public static final String TYPE_SEARCH_MEAN_FR = "meanfr";
    public static final String TYPE_SEARCH_READ_ON = "readon";
    public static final String TYPE_SEARCH_READ_KUN = "readkun";
    public static final String TYPE_SEARCH_ID_KEY_KANJI = "idkeykanji";
    public static final String NAME_LINE_ERROR = "erreur survenue";


    /***********************************************************/
    /**                      ROUTES GET                       **/
    /***********************************************************/

    /**
     * Filtre et pagine une liste de kanjis
     *
     * @param strokes    Byte
     * @param meaningFr  String
     * @param readingKun String
     * @param readingOn  String
     * @param idKeyKanji String
     * @param size       String
     * @param page       String
     * @return ResponseEntity<Map < String, Object>>
     */
    @GetMapping
    @Operation(summary = "Filtrer et paginer une liste de kanjis")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Kanji trouvé",
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Kanji.class))
                    )}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Aucun kanji trouvé",
                    content = @Content),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erreur serveur",
                    content = @Content),
    })
    public ResponseEntity<Map<String, Object>> list(
            @RequestParam(required = false) Byte strokes,
            @RequestParam(required = false) String meaningFr,
            @RequestParam(required = false) String readingKun,
            @RequestParam(required = false) String readingOn,
            @RequestParam(required = false) String idKeyKanji,
            @RequestParam(defaultValue = "5") String size,
            @RequestParam(defaultValue = "1") String page
    ) {
        try {
            Pageable pageable = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(size));
            Map<String, Object> response = new HashMap<>();

            // ENVOI DE LA REQUETE EN FONCTION DU PARAMETRE DE RECHERCHE
            if (meaningFr != null && readingKun == null && readingOn == null && idKeyKanji == null) {
                response = kanjiService.getKanjiBy(TYPE_SEARCH_MEAN_FR, meaningFr, strokes, pageable);
            } else if (meaningFr == null && readingKun != null && readingOn == null && idKeyKanji == null) {
                response = kanjiService.getKanjiBy(TYPE_SEARCH_READ_KUN, readingKun, strokes, pageable);
            } else if (meaningFr == null && readingKun == null && readingOn != null && idKeyKanji == null) {
                response = kanjiService.getKanjiBy(TYPE_SEARCH_READ_ON, readingOn, strokes, pageable);
            } else if (meaningFr == null && readingKun == null && readingOn == null && idKeyKanji != null) {
                response = kanjiService.getKanjiBy(TYPE_SEARCH_ID_KEY_KANJI, idKeyKanji, strokes, pageable);
            } else if (meaningFr == null && readingKun == null && readingOn == null) {
                response = kanjiService.getKanjiBy(null, null, strokes, pageable);
                // SI UTILISATION SIMULTANE DES CHAMPS DE RECHERCHE
            } else {
                response.put(NAME_LINE_ERROR, ERROR_FIELD);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            // LA REQUETE RETOURNE UN TABLEAU VIDE
            if (response == null) {
                Map<String, Object> resNotFound = new HashMap<>();
                resNotFound.put(NAME_LINE_ERROR, ERROR_NOT_FOUND);
                return new ResponseEntity<>(resNotFound, HttpStatus.NOT_FOUND);
            }
            // OK
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
            Kanji kanji = kanjiService.getKanjiById(id);
            if (kanji == null) {
                Map<String, Object> resNotFound = new HashMap<>();
                resNotFound.put(NAME_LINE_ERROR, ERROR_NOT_FOUND);
                new ResponseEntity<>(resNotFound, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(kanjiService.getKanjiById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
