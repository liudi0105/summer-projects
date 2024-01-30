import { Connection as Conn, DbName, Schema } from "../entities";
import { mockConn } from "./index.mock";

export function listDbName(conn: Conn) {

}

export function listSchema(dbName: DbName) {

}

export function listTable(schema: Schema) {

}

export class ConnService {

  static key = 'CONN'

  static listConn = () => {
    let conn = sessionStorage.getItem(this.key)
    if (!conn) {
      sessionStorage.setItem(this.key, JSON.stringify(mockConn))
    }
    conn = sessionStorage.getItem(this.key)
    return JSON.parse(conn!) as Conn[]
  }

  static openConn = (id: string) => {
    const conn: Conn[] = JSON.parse(sessionStorage.getItem(this.key) as string)
    conn.find(v => v.id == id)!.connected = true
    sessionStorage.setItem(this.key, JSON.stringify(conn))
  }
}