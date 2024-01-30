import { Connection, Schema } from "../entities";

export const mockConn: Connection[] = [{
  name: 'mysql',
  id: '1',
  platform: 'MYSQL',
  order: 1
}, {
  name: 'postgresql',
  id: '2',
  platform: 'POSTGRESQL',
  order: 2
}]

export const mockSchema: Schema[] = [{
  name: 'jpa',
  connId: '2',
  id: '1'
}, {
  name: 'test',
  connId: '2',
  id: '2'
}]