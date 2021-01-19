
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

/*
    Modify company
 */
alter table company drop column address;
alter table company drop column movil_phone;
alter table company drop column phone;
alter table company drop column is_client;
alter table company drop column is_provider;

/*
    Add Company Category to permits and rol admin
 */
INSERT INTO public.jhi_permit (id, organization_id, name, resource, created_by, created_date, last_modified_by, last_modified_date)
VALUES (10, 1, 'Categoría Empresa', '/base-data/company-category', 'system', null, 'system', null);

INSERT INTO public.jhi_authority_permit (authority_id, permit_id)
VALUES (1, 10);

/*
    Modify company
 */
alter table company drop column identification;

/*
    Add Company to permits and rol admin
 */

INSERT INTO public.jhi_permit (id, organization_id, name, resource, created_by, created_date, last_modified_by, last_modified_date)
VALUES (11, 1, 'Empresa', '/base-data/company', 'system', null, 'system', null);

INSERT INTO public.jhi_authority_permit (authority_id, permit_id)
VALUES (1, 11);

/*
   Modifying the length of identification and email on company
 */

alter table company alter column identification type varchar(13)
alter table company alter column email type varchar(200)

/*
    Add Bank to permits and rol admin
 */

INSERT INTO public.jhi_permit (id, organization_id, name, resource, created_by, created_date, last_modified_by, last_modified_date)
VALUES (12, 1, 'Banco', '/configuration/bank', 'system', null, 'system', null);

INSERT INTO public.jhi_authority_permit (authority_id, permit_id)
VALUES (1, 12);

/*
    Reorganization of document table
 */
alter table document drop column document_type;
alter table document drop column name;
alter table document drop column prefix;
alter table document drop column sequential;

/*
    Reorganization of the invoice table
 */
alter table invoice_client drop column electronic_document_id;
drop table electronic_document_info;
drop table tax_detail_invoice;
drop table detail_invoice;
drop table tax_invoice;
drop table invoice_client_additional_information;
drop table additional_information;
drop table pay_way;
drop table payment;
drop table invoice_client;

/*
    Modifyng Tax Detail Invoice
 */
alter table tax_detail_invoice alter column rate type real using rate::real;

/*
    Modifying Invoice Client Table
 */
alter table invoice_client rename column total_tax_free to total_without_tax;

/*
    Modifying Tax Invoice Table
 */
alter table tax_invoice drop column percentage_code;


/*
    Permits To Pay Way
 */

/*
    Add Pay Way to permits and rol admin
 */

INSERT INTO public.jhi_permit (id, organization_id, name, resource, created_by, created_date, last_modified_by, last_modified_date)
VALUES (13, 1, 'Pay Way', '/base-data/pay-way', 'system', null, 'system', null);

INSERT INTO public.jhi_authority_permit (authority_id, permit_id)
VALUES (1, 13);
