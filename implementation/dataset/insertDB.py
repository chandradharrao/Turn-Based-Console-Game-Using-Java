import psycopg2
#create pokemon database
conn = psycopg2.connect("host=localhost user=postgres password=pratheek")
conn.autocommit = True
cur = conn.cursor()
drop_db = "DROP DATABASE pokemonDB"
cur.execute(drop_db)
create_db = "CREATE DATABASE pokemonDB"
cur.execute(create_db)
print("database Created successfully.....")
conn.commit()
conn.close()
#change accordingly in your system
dataset_path = "C:\\Users\\Pratheek\\Desktop\\sem 6\\OOAD\\Turn-Based-Console-Game-Using-Java\\implementation\\dataset"

conn2 = psycopg2.connect("host=localhost dbname=pokemondb user=postgres password=pratheek")
conn2.autocommit = True
cur = conn2.cursor()
#create move tables and insert to DB
move_files = ['Fire_moves','Water_moves','Grass_moves']
for file in move_files:
    try:
        drop_move_table = f"DROP TABLE {file}"
        cur.execute(drop_move_table)
    except:
        pass
    create_move_table = (
    f"CREATE TABLE {file}(\n"
    f"ID int NOT NULL,\n"
    f"Name varchar(20),\n"
    f"Type int,\n"
    f"Power int, PP int\n"
    f");"
    )
    cur.execute(create_move_table)
    copy_csv = (
        f"COPY {file}(ID,Name,Type,Power,PP)\n"
        f"FROM '{dataset_path}/{file}.csv' \n"
        f"DELIMITER ','\n"
        f"CSV HEADER;"
    )
    cur.execute(copy_csv)
    print(f"{file} inserted successfully.....")
    disp = f"select * from {file};"
    cur.execute(disp)
    for i in cur.fetchall():
        print(i)
conn2.commit()
conn2.close()

conn3 = psycopg2.connect("host=localhost dbname=pokemondb user=postgres password=pratheek")
conn3.autocommit = True
cur = conn3.cursor()
#create move tables and insert to DB
move_files = ['pokemons']
for file in move_files:
    try:
        drop_move_table = f"DROP TABLE {file}"
        cur.execute(drop_move_table)
    except:
        pass
    create_move_table = (
    f"CREATE TABLE {file}(\n"
    f"ID int NOT NULL,\n"
    f"hp int,\n"
    f"name varchar(25),\n"
    f"type1 int,\n"
    f"evolution int\n"
    f");"
    )
    cur.execute(create_move_table)
    copy_csv = (
        f"COPY {file}(ID,hp,name,type1,evolution)\n"
        f"FROM '{dataset_path}/{file}.csv' \n"
        f"DELIMITER ','\n"
        f"CSV HEADER;"
    )
    cur.execute(copy_csv)
    print(f"{file} inserted successfully.....")
    disp = f"select * from {file};"
    cur.execute(disp)
    for i in cur.fetchall():
        print(i)
conn3.commit()
conn3.close()