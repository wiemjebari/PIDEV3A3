<?php

namespace App\Form;

use Symfony\Component\Form\AbstractType;
use App\Entity\PropertySearch;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class TriJeuType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('Tritype', ChoiceType::class, array('choices'  => array(
            'Par Nom_Asc' => 'Par Nom_Asc',
            'Par Nom_Desc' => 'Par Nom_Desc',
            'Par Cat_Asc' => 'Par Cat_Asc',
            'Par Cat_Desc' => 'Par Cat_Desc',
            'Par Niv_Diff_Asc' => 'Par Niv_Diff_Asc',
            'Par Niv_Diff_Desc' => 'Par Niv_Diff_Desc',
        )

        ));
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            // Configure your form options here
        ]);
    }
}
