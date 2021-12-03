
INSERT INTO KANJI (SYMBOL, STROKES)
VALUES ('一',1),('二',2),('九',2);

INSERT INTO READINGS_ON (ID_KANJI, READING_ON)
VALUES (1,'いち'),(1,'いつ'),(2,'に'),(2,'じ'),(3,'きゅう'),(3,'く');

INSERT INTO READINGS_KUN (ID_KANJI, READING_KUN)
VALUES (1,'ひと-'),(1,'ひと.つ'),(2,'ふた'),(2,'ふた.つ'),(2,'ふたたび'),(3,'ここの'),(3,'ここの.つ');

INSERT INTO MEANINGS_FR (ID_KANJI, MEANING_FR)
VALUES (1,'un'),(2,'deux'),(3,'neuf');

INSERT INTO KEY_KANJI (SYMBOL_KEY, NAME_KEY, STROKES, KEY_NUMBER)
VALUES ('一','un',1,1),('丨','bâton',1,2),('丶','point',1,3),('丿','oblique',1,4),
       ('乀','oblique',1,4),('乁','oblique',1,4),('乙','second',1,5),('乚','second',1,5),('乛','second',1,5),('亅','crochet',1,6),
       ('二','deux',2,7),('亠','couvercle',2,8),('人','être humain',2,9),('亻','être humain',2,9),
       ('儿','jambes',2,10),('入','entrer',2,11),('八','huit',2,12),('丷','huit',2,12),('冂','pourtour',2,13),
       ('冖','couverture',2,14),('冫','glace',2,15),('几','table',2,16),('凵','contenant',2,17),
       ('刀','couteau, épée',2,18),('刂','couteau, épée',2,18),('力','force',2,19),('勹','paquet',2,20),('匕','cuillère',2,21),
       ('匚','boîte',2,22),('匸','coffre',2,23),('十','dix',2,24),('卜','divination',2,25),('卩','sceau',2,26),
       ('厂','fakaise',2,27),('厶','privé',2,28),('又','de nouveau',2,29);

INSERT INTO KANJI_KEY_KANJI (ID_KANJI,ID_KEY_KANJI)
VALUES (1,1),(2,1),(3,7);