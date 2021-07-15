public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    private List<Article> listaArtigos;

    public ArticleAdapter(List<Article> listaArtigos) {
        this.listaArtigos = listaArtigos;
    }
