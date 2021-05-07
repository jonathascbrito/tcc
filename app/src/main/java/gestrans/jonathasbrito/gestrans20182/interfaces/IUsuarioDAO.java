package gestrans.jonathasbrito.gestrans20182.interfaces;

import java.util.List;

import gestrans.jonathasbrito.gestrans20182.models.Usuario;

/**
 * Created by Brito on 19/11/2018.
 */

public interface IUsuarioDAO {

    public boolean salvar(Usuario usuario);
    public boolean atualizar (Usuario usuario);
    public boolean deletar (Usuario usuario);
    public List<Usuario> listar();

}
