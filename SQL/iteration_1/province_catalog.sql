/*
    Pichincha province
 */

INSERT INTO public.province (id, created_by, created_date, last_modified_by, last_modified_date, code, name, organization_id)
VALUES (1, 'admin', '2020-12-13 16:22:23.000000', 'admin', '2020-12-13 16:22:36.000000', '17', 'PICHINCHA', 1);
/*
    Cantons of Pichincha
 */
INSERT INTO public.canton (id, created_by, created_date, last_modified_by, last_modified_date, code, name, province_id)
VALUES (1, 'admin', '2020-12-13 17:23:32.000000', 'admin', '2020-12-13 17:23:41.000000', '1', 'QUITO', 1);
INSERT INTO public.canton (id, created_by, created_date, last_modified_by, last_modified_date, code, name, province_id)
VALUES (2, 'admin', '2020-12-13 17:23:32.000000', 'admin', '2020-12-13 17:23:41.000000', '2', 'CAYAMBE', 1);

/*
    Parishes of Quito
 */
INSERT INTO public.parish (id, created_by, created_date, last_modified_by, last_modified_date, code, name, canton_id)
VALUES (1, 'admin', '2020-12-13 17:41:10.000000', 'admin', '2020-12-13 17:41:14.000000', '1', 'BELISARIO QUEVEDO', 1);

INSERT INTO public.parish (id, created_by, created_date, last_modified_by, last_modified_date, code, name, canton_id)
VALUES (2, 'admin', '2020-12-13 17:41:10.000000', 'admin', '2020-12-13 17:41:14.000000', '2', 'CARCELEN', 1);


/*
    Tungurahua province
 */
INSERT INTO public.province (id, created_by, created_date, last_modified_by, last_modified_date, code, name, organization_id)
VALUES (2, 'admin', '2020-12-13 16:22:23.000000', 'admin', '2020-12-13 16:22:36.000000', '18', 'TUNGURAHUA', 1);


