import {InProgressProps} from "./InProgressProps.ts";

export default function InProgress(props: InProgressProps) {
	return (
		<>
			{props.todos.map((todo) => (
				<div key={todo.id}>
					{todo.id}
					{todo.description}
					{todo.status}
				</div>
			))}
			{props.todos.length === 0 && <div>No In Progress Todos</div>}
		</>
	)
}