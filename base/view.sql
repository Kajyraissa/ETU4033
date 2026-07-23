CREATE VIEW v_modele AS
SELECT
  m.id_modele,
  m.id_marque,
  m.libelle,
  mar.libelle AS libelle_marque
  
FROM modele m
JOIN marque mar ON m.id_marque = mar.id_marque;