import {OpenProps} from "./OpenProps.ts";
import TodoCard from "../../components/TodoCard/TodoCard.tsx";

export default function Open(props : OpenProps) {
	return (
		<div className={"align-top"}>
			{props.todos.map((todo) => (
				<TodoCard key={todo.id} todo={todo} fetchAll={props.fetchAll}/>
			))}
			{props.todos.length === 0 && <div>No Open Todos</div>}
		</div>
	)
}