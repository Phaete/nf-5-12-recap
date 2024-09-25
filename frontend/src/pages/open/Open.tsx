import {OpenProps} from "./OpenProps.ts";
import TodoCard from "../../components/TodoCard/TodoCard.tsx";

export default function Open(props : OpenProps) {
	return (
		<>
			{props.todos.map((todo) => (
				<TodoCard id={todo.id} description={todo.description} status={todo.status}/>
			))}
			{props.todos.length === 0 && <div>No Open Todos</div>}
		</>
	)
}