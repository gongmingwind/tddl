package com.taobao.tddl.optimizer.core.ast.dml;

import com.taobao.tddl.optimizer.core.ASTNodeFactory;
import com.taobao.tddl.optimizer.core.ast.DMLNode;
import com.taobao.tddl.optimizer.core.ast.query.TableNode;
import com.taobao.tddl.optimizer.core.plan.IDataNodeExecutor;
import com.taobao.tddl.optimizer.core.plan.dml.IDelete;
import com.taobao.tddl.optimizer.exceptions.QueryException;

public class DeleteNode extends DMLNode<DeleteNode> {

    public DeleteNode(TableNode table){
        super(table);
    }

    public IDataNodeExecutor toDataNodeExecutor() throws QueryException {
        IDelete delete = ASTNodeFactory.getInstance().createDelete();
        delete.setConsistent(true);
        delete.executeOn(this.getDataNode());
        delete.setQueryTree(this.getNode().toDataNodeExecutor());
        delete.setSchemaName(this.getNode().getSchemaName());
        delete.setIndexName(this.getNode().getIndexUsed().getName());
        return delete;
    }

    public DeleteNode deepCopy() {
        DeleteNode delete = new DeleteNode(null);
        super.deepCopySelfTo(delete);
        return delete;
    }

    public DeleteNode copy() {
        DeleteNode delete = new DeleteNode(null);
        super.copySelfTo(delete);
        return delete;
    }

}
