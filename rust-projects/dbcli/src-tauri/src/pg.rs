use std::collections::{hash_map, HashMap};

use postgres::{Client, NoTls};

pub fn connect_pg() -> Client {
    let mut client =
        Client::connect("host=localhost user=postgres password=example", NoTls).unwrap();
    client
}

#[tauri::command]
pub fn list_db() -> Vec<String> {
    let mut vec = Vec::new();
    for row in connect_pg()
        .query("SELECT datname FROM pg_database", &[])
        .unwrap()
    {
        let s: String = row.get(0);
        vec.push(s);
    }
    vec
}

#[tauri::command]
pub fn get_current_db() -> String {
    let r = connect_pg()
        .query_one("SELECT current_database()", &[])
        .unwrap();
    r.get(0)
}
