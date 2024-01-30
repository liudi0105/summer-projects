export type Platform = 'MYSQL' | 'POSTGRESQL'

export interface Connection {
  platform: Platform
  name: string
  id: string
  order: number
  connected?: boolean
  expand?: boolean
}

export interface DbName {
  name: string
  expand: boolean
}

export interface Schema {
  name: string
  id: string
  connId: string
  expand?: boolean
}

export interface Table {
  name: string
}
