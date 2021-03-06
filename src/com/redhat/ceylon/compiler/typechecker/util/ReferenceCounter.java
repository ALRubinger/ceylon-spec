/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redhat.ceylon.compiler.typechecker.util;

import java.util.HashSet;
import java.util.Set;

import com.redhat.ceylon.compiler.typechecker.model.Declaration;
import com.redhat.ceylon.compiler.typechecker.model.ProducedType;
import com.redhat.ceylon.compiler.typechecker.tree.Tree;
import com.redhat.ceylon.compiler.typechecker.tree.Visitor;

/**
 *
 * @author kulikov
 */
public class ReferenceCounter extends Visitor {
	
	private Set<Declaration> referencedDeclarations = new HashSet<Declaration>();
	
	void inc(Declaration d) {
		referencedDeclarations.add(d);
	}
	
	boolean referenced(Declaration d) {
		return referencedDeclarations.contains(d);
	}
	
    @Override
    public void visit(Tree.MemberOrTypeExpression that) {
        super.visit(that);
        Declaration d = that.getDeclaration();
		if (d!=null) inc(d);
    }
    @Override
    public void visit(Tree.SimpleType that) {
        super.visit(that);
        ProducedType t = that.getTypeModel();
        if (t!=null) {
        	inc(t.getDeclaration());
        }
    }
}
