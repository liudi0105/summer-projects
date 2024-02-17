use postgres::{Client, Error, NoTls};

fn main() {
    getdb().unwrap();
}

pub fn getdb() -> Result<(), Error> {
    let mut client = Client::connect("host=localhost user=postgres password=example", NoTls)?;

    for row in client.query("SELECT datname FROM pg_database", &[])? {
        let s: String = row.get(0);
        println!("dbname: {s}");
    }

    let r = client.query_one("SELECT current_database()", &[])?;

    let name: String = r.get(0);

    println!("current dbname: {name}");

    Ok(())
}
