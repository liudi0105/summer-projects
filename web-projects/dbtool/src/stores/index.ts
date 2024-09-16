import { create } from 'zustand'
import { Connection, Schema } from '../entities'
import { mockConn, mockSchema } from '../services/index.mock'
import { Theme } from '@fluentui/react-components'

interface ConnState {
  conns: Connection[],
  toggleConn: (id: string) => void
  toggleExpand: (id: string) => void
}

export const useConnState = create<ConnState>((set) => ({
  conns: mockConn,
  toggleConn: (id) => set((state) => {
    const conn = state.conns.find(v => v.id == id)
    conn!.connected = !conn!.connected
    return { ...state }
  }),
  toggleExpand: (id) => set((state) => {
    const conn = state.conns.find(v => v.id == id)
    conn!.expand = !conn!.expand
    return { ...state }
  })
}))

interface SchemaState {
  schemas: Schema[]
  toggleSchema: (id: string) => void
}

export const useSchemaState = create<SchemaState>((set) => ({
  schemas: mockSchema,
  toggleSchema: (id) => set((state) => {
    const schema = state.schemas.find(v => v.id == id)
    schema!.expand = !schema!.expand
    return { ...state }
  })
}))

interface ThemeState {
  dark: boolean
  toggleTheme: () => void
}

export const useThemaStore = create<ThemeState>((set) => ({
  dark: false,
  toggleTheme: () => set(state => ({ ...state, dark: !state.dark }))
}))