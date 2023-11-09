package input.components;

import input.visitor.ComponentNodeVisitor;

public interface ComponentNode
{
	Object accept(ComponentNodeVisitor node, Object o);
}