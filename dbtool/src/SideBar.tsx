import { DownOutlined, RightOutlined } from '@ant-design/icons'
import { HTMLAttributes } from "react"
import styled from "styled-components"
import { useConnState, useSchemaState } from "./stores"

const SSideBar = styled.div`
  .connection {
    border: 1px solid #000;
    border-bottom: 0;
    &:last-of-type {
      border-bottom: 1px solid #000;
    }
  }
`

export const SideBar = () => {

  const connState = useConnState()

  return <SSideBar id="side-bar">
    {
      connState.conns.map(v =>
        <Conn
          id={v.id}
          key={v.id}
          className="connection"
          onClick={() => connState.toggleExpand(v.id)}
        >
          {v.name}
        </Conn>)
    }
  </SSideBar>
}

const SConn = styled.div<{ $expand: boolean, $connected: boolean }>`
  .conn {
    ${props => props.$connected ? 'background-color: #aaa' : ''};
    &:hover {
      background-color: ${props => props.$expand ? '#ccc' : '#aaa'};
    }
    cursor: pointer;
  }
`

export const Conn = (props: { id: string } & HTMLAttributes<HTMLDivElement>) => {

  const { id, children, onClick, ...rest } = props

  const conn = useConnState(
    state => state.conns.find(v => v.id == id),
  )!

  const schemas = useSchemaState(state => state)

  return <SConn $connected={!!conn.connected} $expand={!!conn.expand} {...rest}>
    <div className='conn' onClick={onClick}>
      {conn.expand ? <DownOutlined /> : <RightOutlined />}
      {conn.name}
    </div>
    {schemas.schemas
      .filter(v => v.connId == id)
      .map(v => <Schema key={v.id} onClick={() => schemas.toggleSchema(v.id)} id={v.id} />)}
  </SConn>
}

const SSchema = styled.div<{ $expand: boolean }>`
  .schema {
    &:hover {
      background-color: ${props => props.$expand ? '#ccc' : '#aaa'};
    }
    cursor: pointer;
    &::before {
      content: "";
      width: 10px;
      display: inline-block;
    }
  }
`

export const Schema = (props: { id: string } & HTMLAttributes<HTMLDivElement>) => {

  const { id, onClick, ...rest } = props

  const state = useSchemaState(state => state.schemas.find(v => v.id == id))!
  return <SSchema {...rest} $expand={!!state.expand}>
    <div className='schema' onClick={onClick}>
      {!!state.expand ? <DownOutlined /> : <RightOutlined />} {state.name}
    </div>
  </SSchema>
}

const STable = styled.div``

export const Table = () => {
  return <STable></STable>
}