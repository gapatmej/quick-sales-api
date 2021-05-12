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

/*
    Permits To Pay Way
 */

/*
    Add Company Category to permits and rol admin
 */
INSERT INTO jhi_permit (id, organization_id, name, resource, created_by, created_date, last_modified_by, last_modified_date)
VALUES (10, 1, 'Categoría Empresa', '/base-data/company-category', 'system', null, 'system', null);

INSERT INTO jhi_authority_permit (authority_id, permit_id)
VALUES (1, 10);

INSERT INTO jhi_permit (id, organization_id, name, resource, created_by, created_date, last_modified_by, last_modified_date)
VALUES (11, 1, 'Empresa', '/base-data/company', 'system', null, 'system', null);

INSERT INTO jhi_authority_permit (authority_id, permit_id)
VALUES (1, 11);

/*
    Add Bank to permits and rol admin
 */
INSERT INTO jhi_permit (id, organization_id, name, resource, created_by, created_date, last_modified_by, last_modified_date)
VALUES (12, 1, 'Banco', '/configuration/bank', 'system', null, 'system', null);

INSERT INTO jhi_authority_permit (authority_id, permit_id)
VALUES (1, 12);

/*
    Add Pay Way to permits and rol admin
 */

INSERT INTO jhi_permit (id, organization_id, name, resource, created_by, created_date, last_modified_by, last_modified_date)
VALUES (13, 1, 'Pay Way', '/base-data/pay-way', 'system', null, 'system', null);

INSERT INTO jhi_authority_permit (authority_id, permit_id)
VALUES (1, 13);

/*
    Add Invoice Client to permits and rol admin
 */
INSERT INTO jhi_permit (id, organization_id, name, resource, created_by, created_date, last_modified_by, last_modified_date)
VALUES (14, 1, 'Factura Cliente', '/sales/invoice-client', 'system', null, 'system', null);

INSERT INTO jhi_authority_permit (authority_id, permit_id)
VALUES (1, 14);

/*
    Add Document to permits and rol admin
 */
INSERT INTO jhi_permit (id, organization_id, name, resource, created_by, created_date, last_modified_by, last_modified_date)
VALUES (15, 1, 'Documento', '/base-data/document', 'system', null, 'system', null);

INSERT INTO jhi_authority_permit (authority_id, permit_id)
VALUES (1, 15);


