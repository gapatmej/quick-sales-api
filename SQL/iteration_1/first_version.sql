
/*
    Permits
 */

insert into jhi_permit (id,organization_id,name,resource,created_by,last_modified_by)
values
       (3,1,'Sucursal','/configuration/branch-office','system','system'),
       (4,1,'Roles','/admin/authority','system','system'),
       (5,1,'Categorías Producto','/inventary/category','system','system'),
       (6,1,'Bodega','/inventary/cellar','system','system'),
       (7,1,'Producto','/inventary/product','system','system'),
       (8,1,'Impuesto','/inventary/tax','system','system'),
       (9,1,'Unidades','/inventary/unit','system','system');

/*
    Add permit to admin authority
*/
insert into jhi_authority_permit(authority_id, permit_id)
values (1,3),
       (1,4),
       (1,5),
       (1,6),
       (1,7),
       (1,8),
       (1,9);

update jhi_permit set resource='/configuration/organization' where name = 'Organizacion' ;

/*
    Drop sequence
 */
alter table emission_point drop column sequence;



