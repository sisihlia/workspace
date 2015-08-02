//select count(id_exemplaire) from exemplaire where id_livre=1
//select * from emprunt
//select * from exemplaire
//select * from livre
//select * from usager
//insert into usager values(nextval('usager_id_usager_seq'),'yuniyarti','sisihlia','etudiant','sisihlia.yuniyarti@telecom-bretagne.eu')
//select * from usager where id_usager=2
//update usager set nom = 'abdulah', prenom = 'hasaan', statut = 'etudiant', email= 'hasaan.abdullah@telecom-bretagne.com' where id_usager=3;
//insert into emprunt values( 8,3,CURRENT_DATE)
//update emprunt set date_retour = CURRENT_DATE where id_exemplaire = 1
//select * from emprunt where date_retour is not null order by id_exemplaire 
//update livre set isbn10 = '15789462' where id=8;
//delete from livre where id=6
//select * from livre where titre like '%le%'
//select date_retour from emprunt where id_exemplaire =18
//select * from emprunt 
//insert into exemplaire values(nextval('exemplaire_id_exemplaire_seq'),5)
//delete from exemplaire where id_exemplaire = 2
//delete exemplaire from exemplaire inner join emprunt on exemplaire.id_exemplaire = emprunt.id_exemplaire where emprunt.id_exemplaire = 2
//delete e.*, em.* from emprunt as em left join  exemplaire as e on e.id_exemplaire = em.id_exemplaire where e.id_exemplaire = 2
//delete exemplaire.*, emprunte.* from exemplaire  left join emprunt  on exemplaire.id_exemplaire = emprunt.id_exemplaire where exemplaire.id_exemplaire = 2
delete exemplaire, emprunt from exemplaire e join emprunt m on exemplaire.id_exemplaire = emprunt.id_exemplaire where exemplaire.id_exemplaire = 2
//delete e from exemplaire e join emprunt m on e.id_exemplaire = m.id_exemplaire where m.id_exemplaire = 2
//delete from emprunt where id__exemplaire = 2 
//delete from exemplaire where id_exemplaire = 2