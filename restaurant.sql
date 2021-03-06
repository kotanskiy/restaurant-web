PGDMP     8                 
    t         
   restaurant    9.5.3    9.5.3 6    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            �           1262    16503 
   restaurant    DATABASE     h   CREATE DATABASE restaurant WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'C' LC_CTYPE = 'C';
    DROP DATABASE restaurant;
             user    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            �           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    6            �           0    0    public    ACL     �   REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;
                  postgres    false    6                        3079    12355    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            �           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    16532    customer_order    TABLE     �   CREATE TABLE customer_order (
    id integer NOT NULL,
    id_employee integer NOT NULL,
    number_table integer,
    order_date date,
    state boolean
);
 "   DROP TABLE public.customer_order;
       public         user    false    6            �            1259    16545    dish    TABLE     �   CREATE TABLE dish (
    id integer NOT NULL,
    name character varying(20) NOT NULL,
    category character varying(20),
    cost_of real,
    weight real
);
    DROP TABLE public.dish;
       public         user    false    6            �            1259    16537 	   dish_list    TABLE     t   CREATE TABLE dish_list (
    id_customer_order integer NOT NULL,
    id_dish integer NOT NULL,
    count integer
);
    DROP TABLE public.dish_list;
       public         user    false    6            �            1259    16751    employee    TABLE     �   CREATE TABLE employee (
    id integer NOT NULL,
    date_birth character varying(255),
    name character varying(255),
    phone character varying(255),
    "position" character varying(255),
    salary real,
    surname character varying(255)
);
    DROP TABLE public.employee;
       public         user    false    6            �            1259    16553    ingredients    TABLE     _   CREATE TABLE ingredients (
    id integer NOT NULL,
    name character varying(20) NOT NULL
);
    DROP TABLE public.ingredients;
       public         user    false    6            �            1259    16550    ingredients_in_dishes    TABLE     |   CREATE TABLE ingredients_in_dishes (
    id_dish integer NOT NULL,
    id_ingredient integer NOT NULL,
    count integer
);
 )   DROP TABLE public.ingredients_in_dishes;
       public         user    false    6            �            1259    16588    menu    TABLE     ]   CREATE TABLE menu (
    id integer NOT NULL,
    menu_name character varying(20) NOT NULL
);
    DROP TABLE public.menu;
       public         user    false    6            �            1259    16593    menu_dishes    TABLE     u   CREATE TABLE menu_dishes (
    id_menu integer NOT NULL,
    id_dish integer NOT NULL,
    count integer NOT NULL
);
    DROP TABLE public.menu_dishes;
       public         user    false    6            �            1259    16540    ready_dishes    TABLE     �   CREATE TABLE ready_dishes (
    id integer NOT NULL,
    id_dish integer NOT NULL,
    id_employee integer NOT NULL,
    id_order integer NOT NULL,
    ready_date date
);
     DROP TABLE public.ready_dishes;
       public         user    false    6            �            1259    16558    store    TABLE     N   CREATE TABLE store (
    id_ingredient integer NOT NULL,
    count integer
);
    DROP TABLE public.store;
       public         user    false    6            �            1259    24976    users    TABLE     d   CREATE TABLE users (
    id integer NOT NULL,
    name character(20),
    password character(20)
);
    DROP TABLE public.users;
       public         postgres    false    6            u          0    16532    customer_order 
   TABLE DATA                     public       user    false    181   8       x          0    16545    dish 
   TABLE DATA                     public       user    false    184   �8       v          0    16537 	   dish_list 
   TABLE DATA                     public       user    false    182   |9       ~          0    16751    employee 
   TABLE DATA                     public       user    false    190   �9       z          0    16553    ingredients 
   TABLE DATA                     public       user    false    186   |;       y          0    16550    ingredients_in_dishes 
   TABLE DATA                     public       user    false    185   <       |          0    16588    menu 
   TABLE DATA                     public       user    false    188   �<       }          0    16593    menu_dishes 
   TABLE DATA                     public       user    false    189   =       w          0    16540    ready_dishes 
   TABLE DATA                     public       user    false    183   �=       {          0    16558    store 
   TABLE DATA                     public       user    false    187   �=                 0    24976    users 
   TABLE DATA                     public       postgres    false    191   	>       �           2606    16536    customer_order_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY customer_order
    ADD CONSTRAINT customer_order_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.customer_order DROP CONSTRAINT customer_order_pkey;
       public         user    false    181    181            �           2606    16549 	   dish_pkey 
   CONSTRAINT     E   ALTER TABLE ONLY dish
    ADD CONSTRAINT dish_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.dish DROP CONSTRAINT dish_pkey;
       public         user    false    184    184            �           2606    16758    employee_pkey 
   CONSTRAINT     M   ALTER TABLE ONLY employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.employee DROP CONSTRAINT employee_pkey;
       public         user    false    190    190            �           2606    16557    ingredients_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY ingredients
    ADD CONSTRAINT ingredients_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.ingredients DROP CONSTRAINT ingredients_pkey;
       public         user    false    186    186            �           2606    16592 	   menu_pkey 
   CONSTRAINT     E   ALTER TABLE ONLY menu
    ADD CONSTRAINT menu_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.menu DROP CONSTRAINT menu_pkey;
       public         user    false    188    188            �           2606    16544    ready_dishes_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY ready_dishes
    ADD CONSTRAINT ready_dishes_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.ready_dishes DROP CONSTRAINT ready_dishes_pkey;
       public         user    false    183    183            �           2606    16562 
   store_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY store
    ADD CONSTRAINT store_pkey PRIMARY KEY (id_ingredient);
 :   ALTER TABLE ONLY public.store DROP CONSTRAINT store_pkey;
       public         user    false    187    187            �           2606    16794    fk4busn1uu1fc30s9om4u6qqpf5    FK CONSTRAINT     �   ALTER TABLE ONLY ingredients_in_dishes
    ADD CONSTRAINT fk4busn1uu1fc30s9om4u6qqpf5 FOREIGN KEY (id_dish) REFERENCES dish(id);
 [   ALTER TABLE ONLY public.ingredients_in_dishes DROP CONSTRAINT fk4busn1uu1fc30s9om4u6qqpf5;
       public       user    false    185    2025    184                        2606    16819    fkbtobtsfimxy97badf8o8tufwi    FK CONSTRAINT     ~   ALTER TABLE ONLY store
    ADD CONSTRAINT fkbtobtsfimxy97badf8o8tufwi FOREIGN KEY (id_ingredient) REFERENCES ingredients(id);
 K   ALTER TABLE ONLY public.store DROP CONSTRAINT fkbtobtsfimxy97badf8o8tufwi;
       public       user    false    186    2027    187            �           2606    16814    fkl6deajwh93kxip2fdkxdxss6j    FK CONSTRAINT     �   ALTER TABLE ONLY ready_dishes
    ADD CONSTRAINT fkl6deajwh93kxip2fdkxdxss6j FOREIGN KEY (id_order) REFERENCES customer_order(id);
 R   ALTER TABLE ONLY public.ready_dishes DROP CONSTRAINT fkl6deajwh93kxip2fdkxdxss6j;
       public       user    false    183    181    2021            �           2606    16779    fklxsj32e0aod19uo08yw3elkpt    FK CONSTRAINT     �   ALTER TABLE ONLY customer_order
    ADD CONSTRAINT fklxsj32e0aod19uo08yw3elkpt FOREIGN KEY (id_employee) REFERENCES employee(id);
 T   ALTER TABLE ONLY public.customer_order DROP CONSTRAINT fklxsj32e0aod19uo08yw3elkpt;
       public       user    false    181    2033    190            �           2606    16804    fknxtkt06ju5exskq0vdmo9jtnm    FK CONSTRAINT     x   ALTER TABLE ONLY ready_dishes
    ADD CONSTRAINT fknxtkt06ju5exskq0vdmo9jtnm FOREIGN KEY (id_dish) REFERENCES dish(id);
 R   ALTER TABLE ONLY public.ready_dishes DROP CONSTRAINT fknxtkt06ju5exskq0vdmo9jtnm;
       public       user    false    2025    183    184            �           2606    16809    fksavh27rimdm7wk0e2opgcwq20    FK CONSTRAINT     �   ALTER TABLE ONLY ready_dishes
    ADD CONSTRAINT fksavh27rimdm7wk0e2opgcwq20 FOREIGN KEY (id_employee) REFERENCES employee(id);
 R   ALTER TABLE ONLY public.ready_dishes DROP CONSTRAINT fksavh27rimdm7wk0e2opgcwq20;
       public       user    false    183    190    2033            �           2606    16616    id_customer_order_fk    FK CONSTRAINT     �   ALTER TABLE ONLY dish_list
    ADD CONSTRAINT id_customer_order_fk FOREIGN KEY (id_customer_order) REFERENCES customer_order(id);
 H   ALTER TABLE ONLY public.dish_list DROP CONSTRAINT id_customer_order_fk;
       public       user    false    2021    181    182            �           2606    16573 
   id_dish_fk    FK CONSTRAINT     g   ALTER TABLE ONLY ready_dishes
    ADD CONSTRAINT id_dish_fk FOREIGN KEY (id_dish) REFERENCES dish(id);
 A   ALTER TABLE ONLY public.ready_dishes DROP CONSTRAINT id_dish_fk;
       public       user    false    2025    184    183                       2606    16601 
   id_dish_fk    FK CONSTRAINT     f   ALTER TABLE ONLY menu_dishes
    ADD CONSTRAINT id_dish_fk FOREIGN KEY (id_dish) REFERENCES dish(id);
 @   ALTER TABLE ONLY public.menu_dishes DROP CONSTRAINT id_dish_fk;
       public       user    false    2025    184    189            �           2606    16606 
   id_dish_fk    FK CONSTRAINT     p   ALTER TABLE ONLY ingredients_in_dishes
    ADD CONSTRAINT id_dish_fk FOREIGN KEY (id_dish) REFERENCES dish(id);
 J   ALTER TABLE ONLY public.ingredients_in_dishes DROP CONSTRAINT id_dish_fk;
       public       user    false    185    2025    184            �           2606    16621 
   id_dish_fk    FK CONSTRAINT     d   ALTER TABLE ONLY dish_list
    ADD CONSTRAINT id_dish_fk FOREIGN KEY (id_dish) REFERENCES dish(id);
 >   ALTER TABLE ONLY public.dish_list DROP CONSTRAINT id_dish_fk;
       public       user    false    182    2025    184            �           2606    16769    id_employee_fk    FK CONSTRAINT     u   ALTER TABLE ONLY customer_order
    ADD CONSTRAINT id_employee_fk FOREIGN KEY (id_employee) REFERENCES employee(id);
 G   ALTER TABLE ONLY public.customer_order DROP CONSTRAINT id_employee_fk;
       public       user    false    181    190    2033            �           2606    16774    id_employee_fk    FK CONSTRAINT     s   ALTER TABLE ONLY ready_dishes
    ADD CONSTRAINT id_employee_fk FOREIGN KEY (id_employee) REFERENCES employee(id);
 E   ALTER TABLE ONLY public.ready_dishes DROP CONSTRAINT id_employee_fk;
       public       user    false    190    183    2033            �           2606    16583    id_ingredient_fk    FK CONSTRAINT     s   ALTER TABLE ONLY store
    ADD CONSTRAINT id_ingredient_fk FOREIGN KEY (id_ingredient) REFERENCES ingredients(id);
 @   ALTER TABLE ONLY public.store DROP CONSTRAINT id_ingredient_fk;
       public       user    false    2027    187    186            �           2606    24971    id_ingredient_fk    FK CONSTRAINT     �   ALTER TABLE ONLY ingredients_in_dishes
    ADD CONSTRAINT id_ingredient_fk FOREIGN KEY (id_ingredient) REFERENCES ingredients(id);
 P   ALTER TABLE ONLY public.ingredients_in_dishes DROP CONSTRAINT id_ingredient_fk;
       public       user    false    186    2027    185                       2606    16596 
   id_menu_fk    FK CONSTRAINT     f   ALTER TABLE ONLY menu_dishes
    ADD CONSTRAINT id_menu_fk FOREIGN KEY (id_menu) REFERENCES menu(id);
 @   ALTER TABLE ONLY public.menu_dishes DROP CONSTRAINT id_menu_fk;
       public       user    false    2031    188    189            �           2606    16578    id_order_fk    FK CONSTRAINT     s   ALTER TABLE ONLY ready_dishes
    ADD CONSTRAINT id_order_fk FOREIGN KEY (id_order) REFERENCES customer_order(id);
 B   ALTER TABLE ONLY public.ready_dishes DROP CONSTRAINT id_order_fk;
       public       user    false    2021    181    183            u   �   x���v
Q���WH.-.��M-��/JI-R��L�Q�L�O�-�ɯLM�Q�+�MJ�$&� y`U�)�%@vq	��Ts�	uV�0�Q0#u#C3]C]cu����TMk.O��	���LG��RCZ[j��ͧ4��b�!K�� ���      x   �   x����
�0���"�m�C�y�$�a ��*��m�[e��oo+��r��$u���Mw i���H#�@�'e�W��{e�$����ٟv-d�TXۓ�MOc�u�T�����B���o(�17%J��rR�}L��"���"��q�;*E�Df��Y�I�7��w�      v   q   x���v
Q���WH�,Έ��,.Q��L�O.-.��M-��/JI-�Q 
��($��h*�9���+h�(�(jZsyR�,c*�e@-��R��^FT4˘�i��\\ \��/      ~   o  x���Mk�0�"xQYyi�����P6uL'�42�l��������K�L�X<I����y�Nf��9J'�)�r]� �E桌���!t���%xh�RҔ�ZU�Jv=T����om��b��:��>�P�$	�q��3�i_�ް',`�ً�R��1�؜�PH��{��Nڒ�9[���w�1ߞ�؞��E���{X��b�\4m
Ip 2����U��H�0dDq�w�p�|�Z��U���M�O�e�.�(BAh����(�[�i9`lg��B���S��4���b���hYx���vYİ�-�����Y)��j�k���f���d�~�8qrk�2�Ǳ�WUe*1yb�|�OQ�>��E)4ל���g����9�w>      z   �   x���v
Q���W��K/JM�L�+)V��L�Q�K�M�Ts�	uV�0�QPO���N�S״��$^�PcqAfr*�����
�KK�I�h�01��Dm�`w&�g���d�����,5=�԰� �K)M�i�� 6Hv/      y   �   x���v
Q���W��K/JM�L�+)��̋O�,�H-V��L3u�����ҼM�0G�P�`#CMk.O��i��`��`Dm3i`&���T5Ә~7�Q��v� ���I]�4�A���L`ԛP;-� }�P�L#�y#p���� ��      |   F   x���v
Q���W�M�+U��L����sS5�}B]�4u�}������\��j3�i3i�� ;�P      }   f   x���v
Q���W�M�+�O�,�H-V��L��u����Br~i^��B��O�k�����iZsyRb�����fQj����b@�� �a@3�!fpq /8�U      w   
   x���          {   V   x���v
Q���W(.�/JU��L���K/JM�L�+�QH�/�+�Ts�	uV�0�Q0Ӵ��$U�����9u,��g��� ID�         Y   x���v
Q���W(-N-*V��L�Q�K�M�Q(H,..�/J�Ts�	uV�0�QP��/I�+�άT@ u�������������5 ɗ�     