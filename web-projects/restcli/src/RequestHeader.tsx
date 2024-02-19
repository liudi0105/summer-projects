import { DeleteOutlined, PlusOutlined } from "@ant-design/icons"
import { Button, Flex, Input, Table } from "antd"
import { useState } from "react"
import { getUuid } from "./util"

interface KeyValue {
  id?: string
  key?: string
  value?: string
}

export const RequestHeader = () => {

  const defaultData: KeyValue[] = [
    { id: getUuid() }
  ]

  const [data, setData] = useState(defaultData)

  function del(id: string) {
    if (data.length == 1) {
      setData([{ id: getUuid() }])
      return
    }

    const newData = data.filter((v) => v.id != id);
    setData(newData)
  }

  return <Flex vertical gap={8}>
    <Table size='small' pagination={false} rowKey='id' bordered
      dataSource={data}
      columns={[
        { dataIndex: 'id', hidden: true },
        { dataIndex: 'key', title: 'Key', render: (value) => <Input defaultValue={value} /> },
        { dataIndex: 'value', title: 'Value', render: (value) => <Input defaultValue={value} /> },
        {
          title: <Button type='link' onClick={() => setData([...data, { id: getUuid() }])} icon={<PlusOutlined />} size='small' />, width: 40,
          render: (value) => <Button onClick={() => del(value.id)} type='link' icon={<DeleteOutlined />} size='small' />
        }
      ]}
    />
  </Flex>
}