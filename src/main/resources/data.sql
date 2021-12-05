
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
       ('厂','fakaise',2,27),('厶','privé',2,28),('又','de nouveau',2,29),
       ('口','bouche',3,30),('囗','enceinte',3,31),('土','terre',3,32),('士','homme, savant',3,33),('夂','retard',3,34),
       ('夊','lenteur',3,35),('夕','soir',3,36),('大','grand',3,37),('女','femme',3,38),('子','enfant',3,39),
       ('宀','toit',3,40),('寸','pouce (mesure)',3,41),('小','petit',3,42),('尢','infirmité',3,43),('尣','infirmité',4,43),
       ('尸','corps',3,44),('屮','pousse',3,45),('山','montagne',3,46),('川','rivière',3,47),('巛','rivière',3,47),
       ('巜','rivière',2,47),('工','habileté',3,48),('己','moi',3,49),('巾','tissu',3,50),('干','sec',3,51),
       ('幺','très fin, peu',3,52),('广','abri',3,53),('廴','déplacement',3,54),('廾','offrandes',3,55),('弋','javelot',3,56),
       ('弓','arc (de tir)',3,57),('彐','groin',3,58),('彑','groin',3,58),('彡','coiffure, rayons',3,59),('彳','chemin',3,60),
       ('心','coeur',4,61),('忄','coeur',3,61),('戈','lance, bataille',4,62),('戶','porte',4,63),('手','main',4,64),
       ('扌','main',3,64),('支','',4,65),('攵','coup, heurt',4,66),('攴','coup, heurt',4,66),('文','lettre',4,67),
       ('斗','unité de volume',4,68),('斤','hache',4,69),('方','direction',4,70),('无','négation',4,71),('日','soleil, jour',4,72),
       ('曰','dire',4,73),('月','lune, mois',4,74),('木','arbre, bois',4,75),('欠','bailler',4,76),('止','arrêt',4,77),
       ('歹','mort',4,78),('殳','arme',4,79),('毋','mère, non',4,80),('比','comparaison',5,81),('毛','poil',4,82),
       ('氏','clan',4,83),('气','air, souffle',4,84),('水','eau',4,85),('氵','eau',3,85),('火','feu',4,86),
       ('灬','feu',4,86),('爪','ongle',4,87),('爫','ongle',4,87),('父','père',4,88),('爻','mélange',4,89),
       ('爿','bois fendu (partie gauche)',4,90),('丬','bois fendu (partie gauche)',3,90),('片','bois fendu (partie droite)',4,91),
       ('牙','croc',4,92),('牛','bétail',4,93),('牜','bétail',4,93),('犬','chien',4,94),('犭','chien',3,94),
       ('玄','obscurité',5,95),('玉','joyau',5,96),('王','joyau',4,96),('瓜','melon',5,97),('瓦','tuile',5,98),
       ('甘','sucré',5,99),('生','vie, naissance',5,100),('用','utilisation',5,101),('田','rizière',5,102),('疋','rouleau de tissu',5,103),
       ('疒','maladie',5,104),('癶','battre des pieds',5,105),('白','blanc',5,106),('皮','peau',5,107),('皿','assiette',5,108),
       ('目','oeil',5,109),('矛','lance',5,110),('矢','flèche',5,111),('石','pierre',5,112),('示','indiquer, rites',5,113),
       ('礻','indiquer, rites',4,113),('禸','empreintes',5,114),('禾','épi',5,115),('穴','trou',5,116),('立','debout',5,117),
       ('竹','bambou',6,118),('⺮','bambou',6,118),('米','riz',6,119),('糸','fil',6,120),('纟','fil',3,120),
       ('缶','jarre',6,121),('网','filet',6,122),('罒','filet',5,122),('羊','mouton',6,123),('羽','plume',6,124),
       ('老','vieillesse',6,125),('而','de plus',6,126),('耒','charrue',6,127),('耳','oreille',6,128),('聿','pinceau',6,129),
       ('肉','chair',6,130),('臣','vassal, ministre',6,131),('自','soi-même',6,132),('至','atteinte',6,133),('臼','mortier',6,134),
       ('舌','langue',6,135),('舛','erreur',6,136),('舟','bateau',6,137),('艮','désaccord',6,138),('色','couleur',6,139),
       ('艸','herbe, plante',6,140),('艹','herbe, plante',3,140),('虍','tigre',6,141),('虫','ver, insecte',6,142),('血','sang',6,143),
       ('行','aller',6,144),('衣','vêtement',6,145),('衤','vêtement',5,145),('襾','couverture',6,146),('覀','couverture',6,146),
       ('見 ','voir',7,147),('角','corne, coin',7,148),('言','parole',7,149),('讠','parole',2,149),('谷','vallée',7,150),
       ('豆','haricot',7,151),('豖','porc',7,152),('豸','animal',7,153),('貝 ','coquillage',7,154),('赤','rouge',7,155),
       ('走','course',7,156),('足','pied',7,157),('身','corps',7,158),('車 ','véhicule',7,159),('辛','amer, épicé',7,160),
       ('辰','dragon (zodiaque)',7,161),('辵','marcher',7,162),('辶','marcher',3,162),('邑','communauté',7,163),('阝','communauté',3,163),
       ('酉','pichet, jarre',7,164),('釆','séparation',7,165),('里','village',7,166),
       ('金','métal, or',8,167),('長 ','long',8,168),('門 ','porte',8,169),('阜','colline',8,170),('阝','colline',3,170),
       ('隶','pourchasser',8,171),('隹','petit oiseau',8,172),('雨','pluie',8,173),('青','bleu',8,174),('非','erreur, "négation"',8,175),
       ('面','face, surface',9,176),('革','cuir',9,177),('韋 ','peau tannée',10,178),('韭','ail',9,179),('音','son, bruit',9,180),
       ('頁','tête, page',9,181),('風 ','vent',9,182),('飛 ','voler',9,183),('食','manger',9,184),('飠','manger',8,184),
       ('饣','manger',3,184),('首','tête',9,185),('香','senteur',9,186),
       ('馬 ','cheval',10,187),('骨','os',10,188),('高','haut',10,189),('髟','coiffure, cheveux',10,190),('鬥','combat',10,191),
       ('鬯','herbes',10,192),('鬲','tripode',10,193),('鬼','démon',10,194),
       ('魚 ','poisson',11,195),('鳥 ','oiseau',11,196),('鹵','sel',11,197),('鹿','cerf, daim',12,198),('麥 ','blé',11,199),('麻','chanvre',11,200),
       ('黄','jaune',11,201),('黍','millet',12,202),('黒','noir',11,203),('黹','couture',12,204),
       ('黽','grenouille',13,205),('鼎','pichet à trois pied',13,206),('鼓','tambour',13,207),('鼠 ','rat, souris',13,208),
       ('鼻','nez',14,209),('斉','équivalent, égal',8,210),('歯','dent',15,211),('龍','dragon',16,212),('亀','tortue',13,213),('龠','flûte',17,214);

INSERT INTO KANJI_KEY_KANJI (ID_KANJI,ID_KEY_KANJI)
VALUES (1,1),(2,1),(3,7);